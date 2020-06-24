const PORT = process.env.PORT || 3000
const URL_CHILDORDER_API = process.env.URL_CHILDORDER_REST_API || "http://localhost:8081/childorders"
const URL_CHILDORDERBYREFNUM_API = process.env.URL_CHILDORDERBYREFNUM_REST_API || "http://localhost:8081/childordersbyrefnumber"
const URL_MASTERORDER_API = process.env.URL_MASTERORDER_REST_API || "http://localhost:8081/masterorders"
const express = require('express')
const app = express()
const api_helper = require('./API_helper')
app.set('views', './views')
app.set('view engine', 'pug')


app.get('/', function(req, res){
   res.render('form');
});

app.post('/testing', function(req, res){
   console.log(req.body);
   res.send("recieved your request!");
});
 

app.get('/childordersbyrefnum', (req, res) => {
	var uri = URL_CHILDORDERBYREFNUM_API + "/" + req.query.orderRefNum;
    api_helper.make_API_call(uri)
    .then(response => {
		var data = response                    
		//var parsedjson = data.json()
        res.render('child',{childorders: data})
		//res.send(response)
    })
    .catch(error => {
        res.send(error)
    })
})

app.get('/childorders', (req, res) => {
    api_helper.make_API_call(URL_CHILDORDER_API)
    .then(response => {
		var data = response                    
		//var parsedjson = data.json()
        res.render('child',{childorders: data})
		//res.send(response)
    })
    .catch(error => {
        res.send(error)
    })
})


app.get('/masterorder', (req, res) => {
	var uri = URL_MASTERORDER_API + "/" + req.query.masterid;
    api_helper.make_API_call(uri)
    .then(response => {
		var data = response                    
		//var parsedjson = data.json()
        res.render('master',{masterorder: data})
	    //res.send(uri)
		//res.send(response)
    })
    .catch(error => {
        res.send(error)
    })
})

app.get('/masterorders', (req, res) => {
    api_helper.make_API_call(URL_MASTERORDER_API)
    .then(response => {
		var data = response                    
		//var parsedjson = data.json()
        res.render('masters',{masterorders: data})
		//res.send(response)
    })
    .catch(error => {
        res.send(error)
    })
})


app.listen(PORT, () => {
    console.log("Server is up on port "+ PORT)
})
