<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            loadTodoList();

            $('#addTodoForm').submit(function (e) {
                e.preventDefault();
                addTodo();
            });
        });

        function loadTodoList() {
            $.ajax({
                url: 'todos',
                type: 'GET',
                success: function (data) {
                    let table = $('#todoTable');
                    table.empty();

                    for (let i = 0; i < data.length; i++) {
                        let row = $('<tr>');
                        row.append($('<td>').text(data[i].id));
                        row.append($('<td>').text(data[i].task));
                        table.append(row);
                    }
                }
            });
        }

        function addTodo() {
            let taskInput = $('#taskInput');

            $.ajax({
                url: 'todo',
                type: 'POST',
                data: {task: taskInput.val()},
                success: function () {
                    taskInput.val('');
                    loadTodoList(); // 변경된 부분: 추가된 후 목록 다시 불러오기
                }
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

<form id="addTodoForm">
    <label for="taskInput"></label><input type="text" id="taskInput" required>
    <button type="submit">Add</button>
</form>
</body>
</html>
