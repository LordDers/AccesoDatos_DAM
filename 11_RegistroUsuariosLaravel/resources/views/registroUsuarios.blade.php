@extends('layouts.plantilla')
@section('titulo')
    <title>Registro Usuario</title>
@endsection

@section('content')
<form action="/procesamiento" method="POST">
  {{ csrf_field() }}
  <!--<div class="text_form"> DNI </div>-->
  <div class="form-group">
    <label for="exampleInputEmail1">DNI</label>
    <input type="text" class="form-control" id="dni" name="dni" placeholder="00000000A">
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Nombre</label>
    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Apellido</label>
    <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Apellido">
  </div>
  
  <div class="form-group">
    <label for="exampleInputPassword1">Edad</label>
    <input type="number" class="form-control" id="edad" name="edad" placeholder="Edad">
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Email</label>
    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  
  <button type="submit" class="btn btn-primary">Enviar</button>
  <a href="/mostrarUsuarios" class="btn btn-secondary" role="button">Mostrar Usuarios</a>
</form>