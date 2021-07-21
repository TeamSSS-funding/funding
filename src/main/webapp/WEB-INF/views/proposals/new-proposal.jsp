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
            <form action="${pageContext.request.contextPath}/proposals/new" method="post" accept-charset="utf-8">
                <sec:csrfInput/>
                <fieldset id="sign_up" class="ba b--transparent ph0 mh0">
                    <legend class="ph0 mh0 fw6 clip">프로젝트 제안하기</legend>
                    <div class="mt3">
                        <label class="db fw4 lh-copy f6" for="title">프로젝트 이름</label>
                        <input class="pa2 input-reset ba bg-transparent w-100 measure" type="text" name="title"  id="title">
                    </div>
                    <div class="mt3">
                        <label class="db fw4 lh-copy f6" for="targetAmount">목표 금액</label>
                        <input class="pa2 input-reset ba bg-transparent w-100 measure" type="number" name="targetAmount"  id="targetAmount">
                    </div>
                    <div>
                        <label for="description" class="f6 b db mb2">Description</label>
                        <textarea id="description" name="description" class="db border-box hover-black w-100 measure ba b--black-20 pa2 br2 mb2"></textarea>
                    </div>
                </fieldset>
                <div class="mt3"><input class="b ph3 pv2 input-reset ba b--black bg-transparent grow pointer f6" type="submit" value="Submit"></div>
            </form>
        </article>
    </section>
</body>
</html>

