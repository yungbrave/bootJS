// const
//
const url = 'http://localhost:8080/rest/api/users/user'

fetch(url)
    .then(res => res.json())
    .then(data => {
        // data.forEach(user => {
            console.log(data)
        // })
    })
// alert("dasd")