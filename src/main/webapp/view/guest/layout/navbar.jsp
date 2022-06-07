<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid px-2">
	<a class="navbar-brand"> <i
			class="bi bi-mortarboard-fill"></i> FPT Polytechnic
	</a>
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
			aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown1">
		<ul class=" navbar-nav me-auto">
				<li class="nav-item collapse navbar-collapse" id="navbarNavDropdown">
				<span class="dropdown"> <a
						class="nav-link dropdown-toggle nav-link ps-0" ng-model="taikhoan"
						id="navbarDropdownMenuLink" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Category </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<c:forEach items="${listCategory }" var="cate">
							<li><a class="dropdown-item"
								   href="/home/guest/category/${cate.id }/product">${cate.name }</a></li>
						</c:forEach>
					</ul>
			</span>
				</li>

				<li class="nav-item"><a class="nav-link" aria-current="page"
										href="/home/guest/product">Product</a></li>
				<li class="nav-item"><a class="nav-link"
										href="/home/guest/cart">Cart</a></li>
				<li class="nav-item"><a class="nav-link" href=""
										data-bs-toggle="modal" data-bs-target="#exampleModal3">History</a></li>
		</ul>
		<span class="nav-item dropdown"> <a
				class="nav-link dropdown-toggle text-white ps-0" ng-model="taikhoan"
				id="navbarDropdownMenuLink1" href="#" role="button"
				data-bs-toggle="dropdown" aria-expanded="false"> ${!empty sessionScope.user ? sessionScope.user.sdt : "Tài khoản"}
		</a>
			<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<li><a class="dropdown-item" href="/login">Đăng
							nhập</a></li>
					<li><a class="dropdown-item" ng-click="logout()" href="">Đổi
							mật khẩu</a></li>
					<li><a class="dropdown-item" href="/SANGTM_PH17730_ASM/login">Đăng
							xuất</a></li>
			</ul>
		</span>
	</div>

</div>
