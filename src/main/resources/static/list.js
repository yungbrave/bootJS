const url = 'http://localhost:8080/rest/api/users/admin'

fetch(url)
    .then(res => res.json())
    .then(data => {
        data.forEach(user => {
        console.log(user)
        })
    })