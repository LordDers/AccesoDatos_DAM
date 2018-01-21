@extends('layouts.plantilla')
@section('titulo')
    <title>Añadir Aula</title>
@endsection

@section('content')


<form action="/anyadirAula" method="POST">
  {{ csrf_field() }}
  
  <div class="form-group">
    <label for="exampleInputPassword1">Aula</label>
    <input type="number" class="form-control" id="aula" name="aula" placeholder="Aula" value="{{old('aula')}}">
  </div>
  
  <button type="submit" class="btn btn-primary">Enviar</button>
</form>
@endsection