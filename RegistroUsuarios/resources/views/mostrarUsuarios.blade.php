@extends('layouts.plantilla')
@section('titulo')
    <title>Usuarios</title>
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
                <h2>Usuarios</h2>
            </div>
            <div class="pull-right">
                <a class="btn btn-success" href="/registro"> Crear Usuario</a>
            </div>
        </div>
    </div>
    
    <table class="table table-bordered table-dark">
        <tr>
            <th>No</th>
            <th>DNI</th>
            <th>Nombre</th>
            <th width="280px">Operation</th>
        </tr>
        @php ($i = 1)
        @foreach ($users as $usuarios)
        <tr>
            <form action='/accion' method='POST' name='varias'>
                {{ csrf_field() }}
                <input name='id' value='{{$usuarios->id}}' hidden/>
                <td>{{ $i++ }}</td>
                <!--<td>{{ $usuarios->dni}}</td>-->
                <td>{{$usuarios->dni}}</td>
                <td>{{ $usuarios->nombre}}</td>
                <td>
                  <input type="submit" class="btn btn-info" name="buscar" value="Show">
                  <input type="submit" class="btn btn-warning" name="editar" value="Edit">
                  <input type="submit" class="btn btn-danger" name="borrar" value="Delete">
                </td>
            </form>
        </tr>
        @endforeach
    </table>
@endsection