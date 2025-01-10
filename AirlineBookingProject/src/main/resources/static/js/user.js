document.addEventListener("DOMContentLoaded", function() {
    fetchUsers();

    const userForm = document.getElementById("userForm");
    if (userForm) {
        userForm.addEventListener("submit", function(event) {
            event.preventDefault();
            createUser();
        });
    }
});

function fetchUsers() {
    fetch('/api/users')
        .then(response => response.json())
        .then(data => {
            const userList = document.getElementById("userList");
            if (userList) {
                userList.innerHTML = "";
                data.forEach(user => {
                    const listItem = document.createElement("li");
                    listItem.textContent = `User ID: ${user.id}, Name: ${user.name}, Email: ${user.email}, Grade: ${user.grade}`;
                    userList.appendChild(listItem);
                });
            }
        })
        .catch(error => console.error('Error fetching users:', error));
}

function createUser() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;

    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name, email }),
    })
    .then(response => response.json())
    .then(data => {
        console.log('User created:', data);
        fetchUsers();
    })
    .catch(error => console.error('Error creating user:', error));
}
