<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
</head>
<body>
<button id="btn">Post Me</button>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        const button = document.querySelector('#btn');
        button.addEventListener('click', () => {
            console.log("clicked!")
            jQuery.ajax({
                url: '/redirection',
                type: 'POST',
                dataType: 'json',
                data: {
                    coffee: 'cappuccino',
                    cake: 'cheese cake'
                }
            }).done((data) => {
                console.log(data);
                window.location.href = "/redirection#"+data.coffee;
            })
        })
    })


</script>
</body>
</html>
