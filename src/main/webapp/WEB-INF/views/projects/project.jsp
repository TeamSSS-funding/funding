<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<title> </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://unpkg.com/tachyons/css/tachyons.min.css">
<body class="w-100 sans-serif">
<section class="flex items-center">
    <article class="pa4 black-80 w-100">
        <dl class="lh-title pa4 mt0">
            <dt class="f6 b">Id</dt>
            <dd class="ml0">${project.id}</dd>
            <dt class="f6 b mt2">Title</dt>
            <dd class="ml0">${project.name}</dd>
            <dt class="f6 b mt2">Member Id</dt>
            <dd class="ml0">${project.userId}</dd>
            <dt class="f6 b mt2">Description</dt>
            <dd class="ml0">${project.content}</dd>
            <dt class="f6 b mt2">Current Amount</dt>
            <dd class="ml0">${project.currentAmount}</dd>
            <dt class="f6 b mt2">Target Amount</dt>
            <dd class="ml0">${project.targetAmount}</dd>
            <dt class="f6 b mt2">Status</dt>
            <dd class="ml0">${project.status}</dd>
        </dl>
        <form method = "post" action = "/projects/${project.slug}/support" class="pa4 black-80">
            <div class="measure">
                <label for="amount" class="f6 b db mb2">Support Amount</label>
                <input id="amount" name = "amount" class="input-reset ba b--black-20 pa2 mb2 db w-100" type="number">
                <input class="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6 dib" type="submit" value="Support">
            </div>
        </form>
    </article>
</section>
</body>
</html>