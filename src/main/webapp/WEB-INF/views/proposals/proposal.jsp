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
                <dd class="ml0">${proposal.id}</dd>
                <dt class="f6 b mt2">Title</dt>
                <dd class="ml0">${proposal.title}</dd>
                <dt class="f6 b mt2">Member Id</dt>
                <dd class="ml0">${proposal.memberId}</dd>
                <dt class="f6 b mt2">Description</dt>
                <dd class="ml0">${proposal.description}</dd>
                <dt class="f6 b mt2">Target</dt>
                <dd class="ml0">${proposal.targetAmount}</dd>
                <dt class="f6 b mt2">Status</dt>
                <dd class="ml0">${proposal.status}</dd>
            </dl>
            <div class="ph3">
                <form method="post" action="/proposals/${proposal.id}/approve">
                    <input class="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6 dib" type="submit" value="Approve">
                </form>
                <form method="post" action="/proposals/${proposal.id}/reject">
                    <input class="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6 dib" type="submit" value="Reject">
                </form>
            </div>
        </article>
    </section>
</body>
</html>