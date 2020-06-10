const path = require('path')
const express = require('express')
const app = express()
const PORT = process.env.PORT || 3000;

const publicDir = path.join(__dirname, '../public')
console.log(path.join(__dirname, '../public'))
app.use( express.static(publicDir))

app.set('view engine', 'hbs');

app.get('/', function (req, res) {
    res.redirect('/load/1');
})

app.get('/load/:subscribers/', function (req, res) {
    var data = require("./data.json");
    var jsdata = JSON.parse(JSON.stringify(data));
    var filtered = jsdata.sort(function(a, b) { return a.Variable1 < b.Variable1 ? 1 : -1; })
                .slice(0, req.params.subscribers);
    var random = Math.floor(Math.random() * 30) + 1 ;
    var customerName = data[random]["name"];
    var orderId =  "1-" + data[random]["_id"].toString().substring(0, 6);
    var customerId = "1-" + data[random]["_id"].toString().slice(-6);
    res.render('load',{
        subscribers: req.params.subscribers,
        data:data,
        filtered:filtered,
        customerName:customerName.toUpperCase(),
        orderId:orderId.toUpperCase(),
        customerId:customerId.toUpperCase(),
    });
})


app.get('/sendOrder', function (req, res) {
    res.send('Order is Sent')
  })
 
app.listen(PORT, () => {
    console.log("Server is up on port "+ PORT)
})