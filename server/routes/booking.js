const express = require("express")
const bookings = express.Router()
const cors = require("cors")
const jwt = require("jsonwebtoken")
const con = require("../database/db")
const mail = require("../database/mail") //For mailing
bookings.use(cors())

bookings.get("/test",(req,res) =>{   
    res.json({msg: "API Test passed"})
})

bookings.post("/postBooking", (req, res) => { //Continue here
    // TO-DO: Add return response of ID / email is used. 
    console.log("\nOutput received from /postBooking => req.body:")
    console.log(req.body)

    var insertData = {
        email: req.body.email.toLowerCase(),
        name:req.body.name,
        address:req.body.address,
        phoneNumber:req.body.phoneNumber,
        gender:req.body.gender,
        room:req.body.room,
        roomView:req.body.roomView,
        checkIn:req.body.checkIn,
        checkOut:req.body.checkOut,
        specialNote:req.body.specialNote 
    }
    //Switch to accomodate Reservation table
    switch(insertData.room){
        case "Queen 1": case "Queen 2": case "Two Room": case "Three Room": case "Bridal":
            insertData.room = "HR1"
            break;
        case "Cottage Double": case "Cottage Triple":case "Cottage Four":
            insertData.room = "CR1"
            break;
        default:
            res.json({Error: 1})
            break;
    }

    console.log("\n")
    console.log("Processed JSON in local var:")
    console.log(insertData) //Complete JSON data

    var getQuery = "SELECT GuestID FROM Guest"
    con.query(getQuery, function(err,result1){        
        if(err){
            console.log(err)            
        }else{
        //Begin generating new guest ID
        var largestID = result1[result1.length-1]
        largestID = JSON.stringify(largestID) // Now a string
        largestID = largestID.substr(13) // {digit}"}
        largestID = largestID.substring(0,largestID.length -2) // {digit}
        largestID = parseInt(largestID) //Now an integer {digit}

        var guestID = "G" + (largestID + 1) // G + {digit+1}
        console.log(guestID)
        //end of generating new Guest ID
        //Insert into Guest table
        var sqlQueryGuest = "INSERT INTO guest value('" + guestID + "','"+ insertData.name + "','"+ insertData.address + "','" + insertData.phoneNumber + "','" + insertData.email + "','" + insertData.gender + "')"
        con.query(sqlQueryGuest, function(err2, result2){
            if(err2){
                console.log(err2)
            }else{
            console.log(result2.affectedRows + " record(s) inserted (GUEST Table)")
            
            var getQuery2 = "SELECT reservationID FROM reservation"
            con.query(getQuery2, function(err3,result3){      
                if(err3){
                    console.log(err3)
                }else{
                    //Begin generating new reservationID                    
                    var largestID2 = result3[result3.length-1]                    
                    largestID2 = JSON.stringify(largestID2) // Now a string                    
                    largestID2 = largestID2.substr(19) // {digit}"}
                    largestID2 = largestID2.substring(0,largestID2.length -2) // {digit}
                    largestID2 = parseInt(largestID2) //Now an integer {digit}

                    var reservationID = "R" + (largestID2 + 1) // R + {digit+1}
                    console.log(reservationID)
                    //end of generating new reservationID
                    //Insert into reservation table
                    var sqlQueryReservation = "INSERT INTO reservation value('" + reservationID + "','" + insertData.room + "','" + guestID + "','DATE_FORMAT(" + insertData.checkIn + ",'YYYY-MM-DD')',null,'DATE_FORMAT(" + insertData.checkOut + ",'YYYY-MM-DD')',null,null,'Success',null,'" + insertData.specialNote + "')"
                    con.query(sqlQueryReservation, function(err4,result4){
                        if(err4){
                            console.log(err4)
                        }else{
                            console.log(result4.affectedRows + " record(s) inserted (Reservation Table)")
                        }
                    })
                }
            });           

            }
        });
            res.json({ Pass : 1})
        }
    });
        
    // con.query(sqlQuery, function(err, result2){
    //     if(err){
    //         console.log(err)            
	// 	}else{
    //         res.json({ Pass: 1})
    //         console.log(result2.affectedRows + " record(s) updated")
	// 	}
	// });

    // var mailOptions = {
    //     from: 'stackoverkill@outlook.com',
    //     to: insertData.email,
    //     subject: '[Confirmed] Reservation made with Giant Forest Inn Hotel',
    //     text: 'Your reservation has been made! We hope to see you soon!'
    // };

    // transporter.sendMail(mailOptions, function(error, info){
    //     if (error) {
    //     console.log(error);
    //     } else {
    //     console.log('Email sent: ' + info.response);
    //     }
    // });
})

// Below is code examples:
bookings.post("/addwatchlist",(req,res) => {
    console.log(req.body)
    var insertData = {
        ID: req.body.ID.toLowerCase(),
        publicAddress:(req.body.publicAddress).toUpperCase()
    }
    console.log("\n")
    console.log("Watchlist request to insert new poll data into watchlist by:" + insertData.ID)
    console.log(insertData)
    var sqlQuery = "INSERT INTO usersbookmarkpoll value('" + insertData.ID + "','" + insertData.publicAddress + "')";
    con.query(sqlQuery, function(err, result){
        if(err){
            if(err.errno == 1062){ // 1062 = ER_DUP_ENTRY
                res.json({ Error: 1062}) // Already added in DB 
                console.log("Already added in db! ERR: PK Duplication!")
            }else{
                throw err
            }
		}else{
            res.json({ Pass: 1}) //Return notification of added poll to watchlist
            console.log(result.affectedRows + " record(s) updated")
		}
	});
})
bookings.delete("/removewatchlist",(req,res)=>{
    console.log(req.body)

    var deleteData = {
        ID: req.body.ID.toLowerCase(),
        publicAddress:(req.body.publicAddress).toUpperCase()
    }
    console.log("\n")
    console.log("Watchlist request to DELETE a watchlist by:" + deleteData.ID + " of " + deleteData.publicAddress)
    console.log(deleteData)
    var sqlQuery = "DELETE FROM usersbookmarkpoll where ID ='" + deleteData.ID 
                    + "' AND pollIndexAddress = '" + deleteData.publicAddress + "'"
    con.query(sqlQuery, function(err, result){
        if(err){
            throw err
        }else{
            res.json({ Pass: 1}) //Return notification of deleted poll to watchlist
            console.log(result.affectedRows + " record(s) deleted")
        }
    });
})

//Dashboard
bookings.post("/updatePublicAddress", (req, res) => {
    // TO-DO: Add return response of ID / email is used. 
    var updateData = {
        ID: req.body.ID.toLowerCase(),
        publicAddress:req.body.publicAddress.toUpperCase()
    }
    console.log("\n")
    console.log("RECEIVED Update Public Address Request")
    console.log(updateData)
    var sqlQuery = "UPDATE users SET publicAddress ='" +  updateData.publicAddress + "' where id ='" +  updateData.ID + "'";
    con.query(sqlQuery, function(err, result){
        if(err){
            throw err            
		}else{
            res.json({ Pass: 1})
            console.log(result.affectedRows + " record(s) updated")
		}
	});

})

bookings.post("/dashboard", (req, res) => {
    // TO-DO: Add return response of ID / email is used. 
    var dashboardData = {
        ID: req.body.ID.toLowerCase(),
        email: '',
        publicAddress:''
    }

    console.log("\n")
    console.log("RECEIVED Dashboard Request")

    con.query("select email,publicAddress from users where id ='" + dashboardData.ID + "'" , function(err, rows){
        if(err){
			throw err
		}else{
            if(rows.length > 0){
                dashboardData.email = rows[0].email
                dashboardData.publicAddress = rows[0].publicAddress
                console.log(dashboardData)
                let dashboardToken = jwt.sign(dashboardData,process.env.SECRET_KEY,{
                    expiresIn: 120
                })            
                res.send(dashboardToken)
            }else{
                res.status(400).json({error: 'ERROR: Dashboard data cannot be retrieved!'})
            }
		}
	});

})

//Register New Account
bookings.post("/register", (req, res) => {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    // TO-DO: Add return response of ID / email is used. 
    var usersTable = {
        ID: (req.body.ID).toLowerCase(),
        email: (req.body.email).toLowerCase(),
        hashedPassword: req.body.password,
        publicAddress: req.body.publicAddress
    }
    

    
    console.log("\n")
    console.log("RECEIVED REGISTER")
    console.log(usersTable)
   
    var sqlQuery1 = "select id from users where ID = '" + usersTable.ID + "'"
    var sqlQuery2 = "select id from users where email = '" + usersTable.email + "'"
    var sqlQuery = "select id from users where ID = '" + usersTable.ID + "' UNION select email from users where email = '" + usersTable.email + "'"
    
    con.query(sqlQuery, function(err, rows){
        if(err){
            throw err
        }else{
            if(rows.length == 1){                
                if(re.test(rows[0].id)){
                    res.json({Error: 2 }) //2 (true) = email is used
                }else{
                    res.json({Error: 1 }) //1 (false) = username is used
                }
            }else if(rows.length == 0){
                con.query('INSERT into users SET ?',usersTable,(err1,res1) =>{
                    if(err1){
                        throw err1
                    }else{
                        console.log('Insert into users: Success:',res1.insertId)
                        res.json({ Pass: 1})
                    }
                })
            }else{
                res.json({Error: 3}) // Both Username and Email is used                
            }
        }
    })
})


module.exports = bookings