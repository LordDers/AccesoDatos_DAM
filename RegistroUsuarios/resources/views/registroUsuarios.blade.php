@extends('layouts.plantilla')
@section('titulo')
    <title>Registro Usuario</title>
@endsection

@section('content')

@if ($errors->any())
  <div class="alert alert-danger">
    <ul>
      @foreach ($errors->all() as $error)
        <li>{{ $error }}</li>
      @endforeach
    </ul>
  </div><br />
@endif


<form action="/procesamiento" method="POST">
  {{ csrf_field() }}
  <!--<div class="text_form"> DNI </div>-->
  <div class="form-group">
    <label for="exampleInputEmail1">DNI</label>
    <input type="text" class="form-control" id="dni" name="dni" placeholder="00000000A" value="{{old('dni')}}">
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Nombre</label>
    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" value="{{old('nombre')}}">
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Apellido</label>
    <input type="text" class="form-control" id="apellido" name="apellido" placeholder="Apellido" value="{{old('apellido')}}">
  </div>
  
  <div class="form-group">
    <label for="exampleInputPassword1">Edad</label>
    <input type="number" class="form-control" id="edad" name="edad" placeholder="Edad" value="{{old('edad')}}">
  </div>
  
  <div class="form-group">
    <label for="exampleInputEmail1">Email</label>
    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" placeholder="Enter email" value="{{old('email')}}">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  
  <button type="submit" class="btn btn-primary">Enviar</button>
  <a href="/mostrarUsuarios" class="btn btn-secondary" role="button">Mostrar Usuarios</a>
</form>
@endsection