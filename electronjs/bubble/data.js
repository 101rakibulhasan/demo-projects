const express = require('express')
const { ObjectId } = require('mongodb')
const { connectToDb, getDb} = require('./database')


const app = express()
app.use(express.json())

let db

connectToDb((err) => {
    if(!err) {
        app.listen(3000, () => {
            console.log('app listening 3000')
        })
        db = getDb()
    }
})

app.get('/users', (req,res) => {
    const page = req.query.p || 0
    const booksPerPage = 3
    let books = []
    db.collection('users')
        .find()
        .sort({ author: 1})
        .skip(page * booksPerPage)
        .limit(booksPerPage)
        .forEach(book => books.push(book))
        .then(() => {
            res.status(200).json(books)
        })
        .catch(() => {
            res.status(500).json({error: 'Could not fetch the documents'})
        })
})

app.get('/users/:id', (req,res) => {

    if(ObjectId.isValid(req.params.id)){
        db.collection("users")
            .findOne({ _id: new ObjectId(req.params.id) })
            .then(doc => {
                res.status(200).json(doc)
            })
            .catch(err => {
                res.status(500).json({error: "Could not fetch the document"})
            })
    }
    else{
        res.status(500).json({error:"Not a valid doc id"})
    }
})

app.post('/users', (req,res) => {
    const book = req.body

    db.collection('users')
        .insertOne(book)
        .then(result => {
            res.status(201).json(result)
        })
        .catch(err => {
            res.status(500).json({err: "Could not create a new document"})
        })
})

app.delete('/users/:id', (req,res) => {

    if(ObjectId.isValid(req.params.id)){
        db.collection("users")
            .deleteOne({ _id: new ObjectId(req.params.id) })
            .then(result => {
                res.status(200).json(result)
            })
            .catch(err => {
                res.status(500).json({error: "Could not delete the document"})
            })
    }
    else{
        res.status(500).json({error:"Not a valid doc id"})
    }
})

app.patch('users/:id', (req,res) => {
    const updates = req.body



    if(ObjectId.isValid(req.params.id)){
        db.collection("users")
            .updateOne({ _id: new ObjectId(req.params.id) }, {$set: {updates}})
            .then(result => {
                res.status(200).json(result)
            })
            .catch(err => {
                res.status(500).json({error: "Could not update the document"})
            })
    }
    else{
        res.status(500).json({error:"Not a valid doc id"})
    }
})