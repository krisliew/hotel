const mysql = require('mysql')

const con = mysql.createConnection({
	host: "localhost",
	user: "root",
	password:"",
	database:"hotel"
})

con.connect(function(err){
	if(err){
		console.log("Error Log: " + err)
	}else{
		console.log("connected to DB!")
	}
})

module.exports = con


// const Sequelize = require("sequelize")
// const db = {}
// const sequelize = new Sequelize("hotel", "root", "", {
//     host: 'localhost',
//     dialect: 'mysql',
//     operatorsAliases: false,
//     pool: {
//         max: 5,
//         min: 0,
//         acquire: 30000,
//         idle: 10000
//     }
// })

// db.sequelize = sequelize
// db.Sequelize = Sequelize

// module.exports = db