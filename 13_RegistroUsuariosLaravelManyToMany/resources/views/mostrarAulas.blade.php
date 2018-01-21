@extends('layouts.plantilla')
@section('titulo')
    <title>Aulas</title>
@endsection

@section('content')

    @if ($message = Session::get('success'))
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
            <strong>Success!</strong> {{ $message }}
        </div>
    @endif

    @if ($message = Session::get('error'))
        <div class="alert alert-danger alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
            <strong>Error!</strong> {{ $message }}
        </div>
    @endif
    
    <div>
        <div class="col-lg-12 margin-tb">
            <div class="pull-left">
                <h2>Aulas</h2>
            </div>
            <div class="pull-right">
                <a class="btn btn-info" href="/mostrarUsuarios"> Mostrar Usuarios</a>
                <a class="btn btn-success" href="/crearAula"> Crear Aula</a>
            </div>
        </div>
    </div>
    
    <table class="table table-bordered table-dark">
        <tr>
            <th>No</th>
            <th>Número de Aula</th>
            <th width="280px">Operation</th>
        </tr>
        @php ($i = 1)
        @foreach ($aulas as $aulasRegistradas)
        <tr>
            <form action='/accion' method='POST' name='varias'>
                {{ csrf_field() }}
                <input name='id' value='{{$aulasRegistradas->id}}' hidden/>
                <td>{{ $i++ }}</td>
                <td>{{$aulasRegistradas->num_aula}}</td>
                <td>
                  <!--<input type="submit" class="btn btn-danger" name="borrar" value="Delete">-->
                </td>
            </form>
        </tr>
        @endforeach
    </table>
@endsection