<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            loadTodoList();

            document.getElementById('addTodoForm').addEventListener('submit', function (e) {
                e.preventDefault();
                addTodo();
            });
        });

        function addList() {
            fetch("/")
        }

        function loadTodoList() {
            fetch('/v2/todos')
                .then(response => response.json())
                .then(data => {
                    let table = document.getElementById('todoTable');
                    table.innerHTML = '';
                    for (let i = 0; i < data.length; i++) {
                        let row = document.createElement('tr');
                        let idCell = document.createElement('td');
                        idCell.textContent = data[i].id;
                        let taskCell = document.createElement('td');
                        taskCell.textContent = data[i].task;
                        row.appendChild(idCell);
                        row.appendChild(taskCell);
                        table.appendChild(row);
                    }
                });
        }

        function addTodo() {
            let taskInput = document.getElementById('taskInput');
            let task = taskInput.value;

            fetch('/v2/todo', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({task: task})
            })
                .then(() => {
                    taskInput.value = '';
                    loadTodoList();
                });


        }
    </script>
</head>
<body>
<h1>Todo List</h1>

<table id="todoTable">
    <tr>
        <th>ID</th>
        <th>Task</th>
    </tr>
</table>

<form onsubmit="event.preventDefault(); addTodo();">
    <label for="taskInput"></label><input type="text" id="taskInput" required>
    <button type="submit">Add</button>
</form>

<script>
    // 초기 Todo 목록 로드
    loadTodoList();
</script>
</body>
</html>
