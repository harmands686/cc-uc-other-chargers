const PORT = process.env.PORT || 3000
const URL_CHILDORDER_API = process.env.URL_CHILDORDER_REST_API || "http://localhost:8081/childorders"
const URL_MASTERORDER_API = process.env.URL_MASTERORDER_REST_API || "http://localhost:8081/masterorders"
const express = require('express')
const app = express()
const api_helper = require('./API_helper')
app.set('views', './views')
app.set('view engine', 'pug')

app.get('/', (req, res) => {
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

app.get('/master', (req, res) => {
    api_helper.make_API_call(URL_MASTERORDER_API)
    .then(response => {
		var data = response                    
		//var parsedjson = data.json()
        res.render('master',{masterorders: data})
		//res.send(response)
    })
    .catch(error => {
        res.send(error)
    })
})

app.listen(PORT, () => {
    console.log("Server is up on port "+ PORT)
})
