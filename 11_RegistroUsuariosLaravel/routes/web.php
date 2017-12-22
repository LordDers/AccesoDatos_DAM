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

Route::get('/', function () {
    //return view('welcome');
    return redirect('mostrarUsuarios');
});

Route::get('/registro', 'ControladorUsuarios@cargarFormulario');

Route::post('/procesamiento', 'ControladorUsuarios@mostrarDatosFormulario');

Route::get('/mostrarUsuarios', 'ControladorUsuarios@showUsuarios');

Route::post('/editUser', 'ControladorUsuarios@modificarUsuario');

Route::post('/accion', 'ControladorUsuarios@accionesUsuarios');

Route::get('/{cualquierCosa}', function ($name) {
    return redirect('mostrarUsuarios');
})->where('cualquierCosa', '.+');