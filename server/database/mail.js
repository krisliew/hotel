//REF: https://www.w3schools.com/nodejs/nodejs_email.asp
var nodemailer = require('nodemailer');

var transporter = nodemailer.createTransport({
  service: 'hotmail',
  auth: {
    user: 'stackoverkill@outlook.com', //testing junk email
    pass: 'tothemars!' //testing junk email
  }
});

// var mailOptions = {
//   from: 'stackoverkill@outlook.com',
//   to: 'liew_1998@live.com',
//   subject: 'Sending Email using Node.js',
//   text: 'That was easy!'
// };


// transporter.sendMail(mailOptions, function(error, info){
//   if (error) {
//     console.log(error);
//   } else {
//     console.log('Email sent: ' + info.response);
//   }
// });