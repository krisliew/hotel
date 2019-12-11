const express = require("express")
const bookings = express.Router()
const cors = require("cors")
const jwt = require("jsonwebtoken")

const con = require("../database/db")

bookings.use(cors())

process.env.SECRET_KEY = 'secret'

bookings.get("/test",(req,res) =>{   
    res.json({msg: "Test passed"})
})

bookings.post("/postBooking", (req, res) => { //Continue here
    // TO-DO: Add return response of ID / email is used. 
    console.log(req.body)
    var insertData = {
        ID: req.body.ID.toLowerCase(),
        publicAddress:(req.body.publicAddress).toUpperCase()
    }
    console.log("\n")
    console.log("/postBooking JSON:" + insertData)
    console.log(insertData)
    var sqlQuery = "INSERT INTO userspoll value('" + insertData.ID + "','" + insertData.publicAddress + "')";
    con.query(sqlQuery, function(err, result){
        if(err){
            console.log(err)            
		}else{
            res.json({ Pass: 1})
            console.log(result.affectedRows + " record(s) updated")
		}
	});

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