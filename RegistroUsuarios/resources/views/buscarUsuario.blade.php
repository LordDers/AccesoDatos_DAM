@extends('layouts.plantilla')
@section('titulo')
    <title>Busqueda</title>
@endsection

@section('content')
    <div>
        <div class="col-lg-12 margin-tb">
            <div class="pull-left">
                <!--<h2>Nombre del usuario</h2>-->
                <h2>{{$users->nombre}}</h2>
            </div>
            <div class="pull-right">
                <a class="btn btn-info" href="/mostrarUsuarios"> Mostrar Usuarios</a>
            </div>
        </div>
    </div>
    
    <table class="table table-bordered table-dark">
        <tr>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Edad</th>
            <th>Email</th>
        </tr>
        <tr>
            <td>{{$users->dni}}</td>
            <td>{{$users->nombre}}</td>
            <td>{{$users->apellido}}</td>
            <td>{{$users->edad}}</td>
            <td>{{$users->email}}</td>
        </tr>
    </table>
@endsection