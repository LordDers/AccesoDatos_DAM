@extends('layouts.plantilla')
@section('titulo')
    <title>Busqueda</title>
@endsection

@section('content')
    <div>
        <div class="col-lg-12 margin-tb">
            <div class="pull-left">
                <!--<h2>Nombre del usuario</h2>-->
                <h2>{{$aula->num_aula}}</h2>
            </div>
            <div class="pull-right">
                <a class="btn btn-info" href="/mostrarUsuarios"> Mostrar Aulas</a>
            </div>
        </div>
    </div>
    
    <table class="table table-bordered table-dark">
        <tr>
            <th>Aula</th>
            <th>Usuarios</th>
        </tr>
        <tr>
            <td>{{$aula->num_aula}}</td>
            <td>
                <ul class="list-group">
                @foreach($usuarios as $usuariosAula)
                    <li class="list-group-item list-group-item-info">{{$usuariosAula->dni}}</li>
                @endforeach
                </ul>
            </td>
        </tr>
    </table>
@endsection