Here is the **same content again exactly as before**, no changes, no reductions, full clean version:

---

# **EX.NO: 10**

---

# **IMPLEMENT NO SQL DATABASE OPERATIONS: CRUD OPERATIONS, ARRAYS USING MONGODB**

---

## **AIM:**

To perform basic CRUD operations and array operations using MongoDB NoSQL database.

---

# **CRUD OPERATIONS IN MONGODB**

CRUD stands for:

* **C – Create (Insert)**
* **R – Read (Query)**
* **U – Update**
* **D – Delete**

MongoDB stores data in **collections** as **documents** (similar to JSON).

---

# **1. INSERTING A DOCUMENT (CREATE)**

To insert a document into a collection:

```js
db.student.insert({
    regNo: "3014",
    name: "Test Student",
    course: {
        courseName: "MCA",
        duration: "3 Years"
    },
    address: {
        city: "Bangalore",
        state: "KA",
        country: "India"
    }
})
```

---

# **2. QUERYING A DOCUMENT (READ)**

Retrieve all documents:

```js
db.student.find()
```

Retrieve a specific record:

```js
db.student.find({ "regNo": "3014" })
```

---

# **3. UPDATING A DOCUMENT (UPDATE)**

Update the *name* of a student with regNo 3014:

```js
db.student.update(
    { "regNo": "3014" },
    {
        $set: {
            "name": "Viraj"
        }
    }
)
```

---

# **4. REMOVING A DOCUMENT (DELETE)**

Delete a record:

```js
db.student.remove({ "regNo": "3014" })
```

---

# **WORKING WITH ARRAYS IN MONGODB**

Documents can contain arrays.
Example: A blog post with multiple comments.

---

# **Sample Document Structure**

```js
{
    "_id" : ObjectId("5ec55af811ac5e2e2aafb2b9"),
    "name" : "Working with Arrays",
    "user" : "Database Rebel",
    "desc" : "Maintaining an array of objects in a document",
    "content" : "some content...",
    "created" : ISODate(),
    "updated" : ISODate(),
    "tags" : ["mongodb", "arrays"],
    "comments" : [
        {
            "user" : "DB Learner",
            "content" : "Nice post.",
            "updated" : ISODate()
        }
    ]
}
```

---

# **CREATING A BLOG POST DOCUMENT**

Select database:

```js
use blogs
```

Create a post:

```js
NEW_POST = {
    name: "Working with Arrays",
    user: "Database Rebel",
    desc: "Maintaining an array of objects in a document",
    content: "some content...",
    created: ISODate(),
    updated: ISODate(),
    tags: ["mongodb", "arrays"]
}

db.posts.insertOne(NEW_POST)
```

---

# **QUERY THE INSERTED DOCUMENT**

```js
db.posts.findOne()
```

---

# **ADDING AN ARRAY ELEMENT (ADD COMMENT)**

```js
NEW_COMMENT = {
    user: "DB Learner",
    text: "Nice post, can I know more about the arrays in MongoDB?",
    updated: ISODate()
}

db.posts.updateOne(
    { _id: ObjectId("5ec55af811ac5e2e2aafb2b9") },
    { $push: { comments: NEW_COMMENT } }
)
```

---

# **UPDATING A SPECIFIC ARRAY ELEMENT**

```js
NEW_CONTENT = "Thank you, please look for updates - updated the post"

db.posts.updateOne(
    { _id: ObjectId("5ec55af811ac5e2e2aafb2b9"), "comments.user": "Database Rebel" },
    { $set: { "comments.$.text": NEW_CONTENT } }
)
```

---

# **DELETING AN ARRAY ELEMENT**

```js
db.posts.updateOne(
    { _id: ObjectId("5ec55af811ac5e2e2aafb2b9") },
    { $pull: { comments: { user: "Database Rebel" } } }
)
```

---

# **ADDING A NEW FIELD TO ALL ARRAY ELEMENTS**

```js
db.posts.updateOne(
    { _id: ObjectId("5ec55af811ac5e2e2aafb2b9") },
    { $set: { "comments.$[].likes": 0 } }
)
```

---

# **UPDATING A SPECIFIC ARRAY ELEMENT BASED ON CONDITION**

Add another comment:

```js
NEW_COMMENT = {
    user: "DB Learner",
    text: "Thanks for the updates!",
    updated: ISODate()
}

db.posts.updateOne(
    { _id: ObjectId("5ec55af811ac5e2e2aafb2b9") },
    { $push: { comments: NEW_COMMENT } }
)
```

Update only the comment where likes field is missing:

```js
db.posts.updateOne(
    { _id: ObjectId("5ec55af811ac5e2e2aafb2b9") },
    { $inc: { "comments.$[ele].likes": 1 } },
    { arrayFilters: [ { "ele.user": "DB Learner", "ele.likes": { $exists: false } } ] }
)
```

---

# **RESULT:**

Thus, NoSQL CRUD operations and array operations were successfully implemented using MongoDB.

---

If you want the **PDF version**, say **"give pdf"**.
