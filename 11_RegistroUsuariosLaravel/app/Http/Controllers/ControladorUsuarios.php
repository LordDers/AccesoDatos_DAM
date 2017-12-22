<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Usuario;

class ControladorUsuarios extends Controller
{
    public function cargarFormulario() {
    	return View('registroUsuarios');
    }

    // anyadir
    public function mostrarDatosFormulario(Request $request) {
    	/*echo $request-> input('dni') . '<br>';
    	echo $request-> input('nombre') . '<br>';
    	echo $request-> input('apellido') . '<br>';
    	echo $request-> input('edad') . '<br>';
    	echo $request-> input('email');*/

    	// *** introducir datos a la tabla 'Usuarios' del modelo 'Usuario' ***

    	// instacia del modelo
    	/*$usuario = new Usuario;

    	// modificar campos del modelo
    	$usuario-> dni = $request-> input('dni');
    	$usuario-> nombre = $request-> input('nombre');
    	$usuario-> apellido = $request-> input('apellido');
    	$usuario-> edad = $request-> input('edad');
    	$usuario-> email = $request-> input('email');

    	// registro en la tabla
    	$usuario-> save();*/
    	
    	Usuario::create($request->all());
    	
    	return redirect('mostrarUsuarios')
    	            ->with('success','Usuario creado correctamente');
    }


    public function showUsuarios() {
    	// mostrar todos los usuarios
    	$lista = Usuario::all();

    	// pasar a la vista el array
    	return View('mostrarUsuarios', ['users' => $lista]);
    }

    public function modificarUsuario(Request $request) {
    	$usuario = Usuario::find($request-> input('id'));

        /*$usuario->dni = $request-> input('dni');
        $usuario->nombre = $request-> input('nombre');
        $usuario->apellido = $request-> input('apellido');
        $usuario->edad = $request-> input('edad');
        $usuario->email = $request-> input('email');
        
        $usuario->save();*/
        
        $usuario->update($request->all());
    	
    	return redirect('mostrarUsuarios')
    	            ->with('success','Usuario modificado correctamente');
    }
    
    public function accionesUsuarios(Request $request) {
        
        $id = $request-> input('id');
        
        if ($request->input('buscar')) {            
            $usuario = Usuario::where('id', $id)->first();
            
            return View('buscarUsuario', ['users' => $usuario]);
            
    	    
        } else if ($request->input('editar')) {
            //$usuario = Usuario::where('id', $id)->get();
            $usuario = Usuario::where('id', $id)->first();
            return View('editarUsuario', ['users' => $usuario]);
            //return View('editarUsuario');

        } else if ($request->input('borrar')) {
            
            $usuario = Usuario::where('id', $id)->delete();
    	    return redirect('mostrarUsuarios')
    	                ->with('success','Usuario borrado correctamente');
        }
    }
}
