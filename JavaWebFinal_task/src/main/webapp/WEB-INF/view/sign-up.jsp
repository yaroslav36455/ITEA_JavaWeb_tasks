<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="includes/header.jsp"%>
	<center>
		<div id="sign-up-success" hidden>Регистрация прошла успешно</div>
		<table id="sign-up-table" border="0">
			<tr>
				<td>Имя:</td>
				<td><input id="name" type="text" required onkeyup="handleNameInput()"/></td>
				<td>
				    <ul>
				        <li id="namePresent">Имя присутствует</li>
				        <li id="nameLength">Длина имени находится в пределах от 2 до 15 символов</li>
				        <li id="nameCharCategory">Присутствуют только символы A-Z и a-z</li>
				        <li id="nameCase">Первый символ в верхнем регистре, а все прочие - в нижнем</li>
				    </ul>
				</td>
			</tr>
			<tr>
				<td>Логин:</td>
				<td>
				    <input id="login" type="text" autocomplete="on"
				            required onkeyup="handleLoginInput()"/>
				</td>
				<td>
				    <ul>
				        <li id="loginPresent">Логин присутствует</li>
				        <li id="loginLength">Длина логина находится в пределах от 6 до 14 символов</li>
				        <li id="loginCharCategory">Присутствуют только символы A-Z, a-z, 0-9 и пунктуации</li>
				    </ul>
				</td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td>
				    <input id="password" type="password" autocomplete="on" 
				            required onkeyup="handlePasswordInput()"/>
				</td>
				<td>
				    <ul>
				        <li id="passwordPresent">Пароль присутствует</li>
				        <li id="passwordLength">Длина пароля находится в пределах от 8 до 15 символов</li>
				        <li id="passwordUppercaseLetters">Символы A-Z присутствуют</li>
				        <li id="passwordLowercaseLetters">Символы a-z присутствуют</li>
				        <li id="passwordDigits">Символы 0-9 присутствуют</li>
				        <li id="passwordPunctuations">Символы пунктуации присутствуют</li>
				    </ul> 
                </td>
			</tr>
			<tr>
				<td>Повторите пароль:</td>
				<td>
				    <input id="retypePassword" type="password"
				            required onkeyup="handleRetypePasswordInput()"/>
				</td>
				<td>
				    <ul id="retypePasswordTip" hidden="true">
                        <li id="retypePasswordMatch">Пароли совпадают</li>
                    </ul>
				</td>
			</tr>
			<tr>
				<td>e-mail:</td>
				<td>
				    <input id="email" type="text" autocomplete="on" required onkeyup="handleEmailInput()"/>
				</td>
				<td id="emailTip">
				    <ul>
                        <li id="emailPresent">Почта присутствует</li>
                        <li id="emailCorrect">Почта корректна</li>
                        <li id="emailLength">Длина почты не более 100 символов</li>
                    </ul>
				</td>
			</tr>
			<tr>
				<td>Адрес:</td>
				<td>
				    <select id="address">
						<option value="DNR">Донецкая Народная Республика</option>
						<option value="LNR">Луганская Народная Республика</option>
						<option value="KNR">Крымская Народная Республика</option>
				    </select>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>Пол:</td>
				<td id="gender">
				    Mужской<input value="MALE" type="radio" name="gen" checked/>
				    Женский<input value="FEMALE" type="radio" name="gen" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td>Комментарий:</td>
				<td><textarea id="comment" cols="30" rows="10" required onkeyup="handleCommentInput()"></textarea></td>
				<td id="commentTip">
				    <ul>
                        <li id="commentPresent">Комментарий присутствует</li>
                        <li id="commentLength">Количество символов 
                            <span id="commentChars">0</span>/<span id="commentCharsTotal">256</span></li>
                    </ul>
				</td>
			</tr>
			<tr>
				<td>Я согласен/согласна установить Амига браузер:</td>
				<td><input id="amigaAgree" type="checkbox" required onchange="handleAmigaAgreeInput()"/></td>
				<td>
				    <ul id="amigaAgreeTip">
                        <li id="amigaAgreePresent">Принято</li>
                    </ul>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="right">
					<div class="sendingExceptionMessage" hidden id="loginAndOrEmailUsed">Ошибка: такие логин и/или email уже используются</div>
					<div class="sendingExceptionMessage" hidden id="invalidField">Ошибка: вы не заполнили или заполнили некорректно одно или боллее полей</div>
					<button onclick="signUp()">Отправить</button>
				</td>
			</tr>
		</table>
	</center>
<%@ include file="includes/footer.jsp"%>
