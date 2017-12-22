@extends('layouts.plantilla')
@section('titulo')
    <title>Editar Usuario</title>
@endsection

@section('content')
    
    <form action="/editUser" method="POST">
        {{ csrf_field() }}
        <!--<div class="text_form"> DNI </div>-->
        <div class="form-group">
            <label for="exampleInputEmail1">DNI</label>
            <input type="text" class="form-control" id="dni" name="dni" value="{{$users->dni}}">
        </div>
        
        <div class="form-group">
            <label for="exampleInputEmail1">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="{{$users->nombre}}">
        </div>
        
        <div class="form-group">
            <label for="exampleInputEmail1">Apellido</label>
            <input type="text" class="form-control" id="apellido" name="apellido" value="{{$users->apellido}}">
        </div>
        
        <div class="form-group">
            <label for="exampleInputPassword1">Edad</label>
            <input type="number" class="form-control" id="edad" name="edad" value="{{$users->edad}}">
        </div>
        
        <div class="form-group">
            <label for="exampleInputEmail1">Email</label>
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" value="{{$users->email}}">
        </div>
        
        <input name='id' value='{{$users->id}}' hidden/>
        <button type="submit" class="btn btn-warning">Editar</button>
        <a href="/mostrarUsuarios" class="btn btn-secondary" role="button">Mostrar Usuarios</a>
    </form>
@endsection