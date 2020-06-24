var xmlparser = require('express-xml-bodyparser');
var http = require('http');
const path = require('path')
const express = require('express')
const app = express()
const PORT = process.env.PORT || 3000;
const URL_BULK_SERVICE_HOST = process.env.URL_BULK_SERVICE || "localhost";
const URL_BULK_SERVICE_PORT = process.env.URL_BULK_SERVICE_PORT || 8080;


const publicDir = path.join(__dirname, '../public')
console.log(path.join(__dirname, '../public'))
app.use( express.static(publicDir))
app.use(xmlparser());
app.set('view engine', 'hbs');

app.get('/', function (req, res) {
    res.redirect('/load/5');
})

app.get('/load/:subscribers/', function (req, res) {
    var data = require("./data.json");
    var jsdata = JSON.parse(JSON.stringify(data));
    var filtered = jsdata.sort(function(a, b) { return a.Variable1 < b.Variable1 ? 1 : -1; })
                .slice(0, req.params.subscribers);
    var random = Math.floor(Math.random() * 30) + 1 ;
    var customerName = data[random]["name"];
    var orderId =  "1-" + data[random]["_id"].toString().slice(-5);
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

app.post('/customerorder', function (req, res) {
console.log(req.rawBody)
res.send('Order  sent')
	// prepare the header
	var postheaders = {
	    'Content-Type' : 'application/xml'
	};

	// the post options
	var optionspost = {
	    host : URL_BULK_SERVICE_HOST,
	    port : URL_BULK_SERVICE_PORT,
	    path : '/customerorder',
	    method : 'POST',
	    headers : postheaders
	};

	console.info('Options prepared:');
	console.info(optionspost);
	console.info('Do the POST call');

	// do the POST call
	var reqPost = http.request(optionspost, function(res) {
	    console.log("statusCode: ", res.statusCode);
	    // uncomment it for header details
	//  console.log("headers: ", res.headers);
 
 	   res.on('data', function(d) {
	        console.info('POST result:\n');
	        process.stdout.write(d);
        	console.info('\n\nPOST completed');
	    });
	 });
	// write the json data
	reqPost.write(req.rawBody);
	reqPost.end();
	reqPost.on('error', function(e) {
	console.error(e);
	});
	
  })
 
app.listen(PORT, () => {
    console.log("Server is up on port "+ PORT)
})
