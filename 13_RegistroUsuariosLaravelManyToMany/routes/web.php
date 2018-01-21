<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/


// ****** Usuarios ******

Route::get('/', function () {
    //return view('welcome');
    return redirect('mostrarUsuarios');
});

Route::get('/registro', 'ControladorUsuarios@cargarFormulario');

Route::post('/procesamiento', 'ControladorUsuarios@mostrarDatosFormulario');

Route::get('/mostrarUsuarios', 'ControladorUsuarios@showUsuarios');

Route::post('/editUser', 'ControladorUsuarios@modificarUsuario');

Route::get('/accion', 'ControladorUsuarios@accionesUsuarios');
Route::post('/accion', 'ControladorUsuarios@accionesUsuarios');


// ****** Aulas ******
Route::get('crearAula', 'ControladorAulas@cargarFormularioAulas');

Route::post('anyadirAula', 'ControladorAulas@procesarAnyadirAula');

Route::get('/mostrarAulas', 'ControladorAulas@showAulas');

Route::get('/{cualquierCosa}', function ($name) {
    return redirect('mostrarUsuarios');
})->where('cualquierCosa', '.+');