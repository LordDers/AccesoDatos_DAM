@extends('layouts.plantilla')
@section('titulo')
    <title>Editar Usuario</title>
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
    
<form action="/editUser" method="POST">
    {{ csrf_field() }}
    <!--<div class="text_form"> DNI </div>-->
    <div class="form-group">
        <label for="exampleInputEmail1">DNI</label>
        @if ($errors->any())
            <input type="text" class="form-control" id="dni" name="dni" value="{{old('dni')}}">
        @else
            <input type="text" class="form-control" id="dni" name="dni" value="{{$users->dni}}">
        @endif
    </div>
    
    <div class="form-group">
        <label for="exampleInputEmail1">Nombre</label>
        @if ($errors->any())
            <input type="text" class="form-control" id="nombre" name="nombre" value="{{old('nombre')}}">
        @else
            <input type="text" class="form-control" id="nombre" name="nombre" value="{{$users->nombre}}">
        @endif
    </div>
    
    <div class="form-group">
        <label for="exampleInputEmail1">Apellido</label>
        @if ($errors->any())
            <input type="text" class="form-control" id="apellido" name="apellido" value="{{old('apellido')}}">
        @else
            <input type="text" class="form-control" id="apellido" name="apellido" value="{{$users->apellido}}">
        @endif
    </div>
    
    <div class="form-group">
        <label for="exampleInputPassword1">Edad</label>
        @if ($errors->any())
            <input type="number" class="form-control" id="edad" name="edad" value="{{old('edad')}}">
        @else
            <input type="number" class="form-control" id="edad" name="edad" value="{{$users->edad}}">
        @endif
    </div>
    
    <div class="form-group">
        <label for="exampleInputEmail1">Email</label>
        @if ($errors->any())
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp"  value="{{old('email')}}">
        @else
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="{{$users->email}}">
        @endif
    </div>
    
    @if ($errors->any())
        <input name='id' value="{{old('id')}}" hidden/>
    @elseif (empty($users->dni))
        <input name='id' value="-1" hidden/>
        <script>window.location.href = "/";</script>
    @else
        <input name='id' value='{{$users->id}}' hidden/>
    @endif
    <button type="submit" name="submit" class="btn btn-warning">Editar</button>
    <a href="/mostrarUsuarios" class="btn btn-secondary" role="button">Mostrar Usuarios</a>
</form>
@endsection