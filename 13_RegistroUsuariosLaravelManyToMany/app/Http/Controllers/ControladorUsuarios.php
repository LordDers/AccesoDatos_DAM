<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Validation\Rule;
use App\Usuarios;
use App\Aulas;

class ControladorUsuarios extends Controller
{
    public function cargarFormulario() {

        $lista = Aulas::all();
        
        return View('registroUsuarios', ['aulas' => $lista]);


    	//return View('registroUsuarios');
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

        // Validate
        $usuarios = $request->validate([
            'dni' => 'required|max:9|unique:usuarios',
            'nombre' => 'required',
            'apellido' => 'required',
            'edad' => 'required|numeric',
            'email' => 'required|email|unique:usuarios'
        ], [
            'dni.required' => 'DNI requerido',
            'dni.unique' => 'El DNI ya existe',
            'dni.max' => 'El DNI tiene que tener un máximo de 9 caracteres',
            'edad.numeric' => 'La edad debe ser numérico',
            'email.email' => 'El email tiene que tener formato de E-mail',
            'email.unique' => 'El email ya existe'
        ]);

        $usuario = new Usuarios;

        // modificar campos del modelo
        $usuario-> dni = $request-> input('dni');
        $usuario-> nombre = $request-> input('nombre');
        $usuario-> apellido = $request-> input('apellido');
        $usuario-> edad = $request-> input('edad');
        $usuario-> email = $request-> input('email');

        // registro en la tabla
        $usuario-> save();


        $aulasForm = $request->input('aula');
        $usuario->aulas()->attach($aulasForm); // id

        return redirect('mostrarUsuarios')
                    ->with('success','Usuario creado correctamente');
    }


    public function showUsuarios() {
    	// mostrar todos los usuarios
    	$lista = Usuarios::all();

    	// pasar a la vista el array
    	return View('mostrarUsuarios', ['users' => $lista]);
    }

    public function modificarUsuario(Request $request) {

        if ($request-> input('id') == -1) {
            return redirect('mostrarUsuarios')
                        ->with('error','Usuario NO modificado');
        } else {
            $usuario = Usuarios::find($request-> input('id'));

            /*$usuario->dni = $request-> input('dni');
            $usuario->nombre = $request-> input('nombre');
            $usuario->apellido = $request-> input('apellido');
            $usuario->edad = $request-> input('edad');
            $usuario->email = $request-> input('email');
            
            $usuario->save();*/

            // Validate
            $usuarios = $request->validate([
                'dni' => [
                    'required',
                    'max:9',
                    Rule::unique('usuarios')->ignore($usuario->id, 'id'),
                ],
                'nombre' => 'required',
                'apellido' => 'required',
                'edad' => 'required|numeric',
                'email' => 'required|email'
            ], [
                'dni.max' => 'El DNI tiene que tener un máximo de 9 caracteres',
                'edad.numeric' => 'La edad debe ser numérico',
                'email.email' => 'El email tiene que tener formato de E-mail',
            ]);
            
            
            $usuario->update($request->all());
            
            return redirect('mostrarUsuarios')
                        ->with('success','Usuario modificado correctamente');
                    }
    }
    
    public function accionesUsuarios(Request $request) {
        
        $id = $request-> input('id');

        if ($request->isMethod('post')) {
            //echo "post";
            if ($request->input('buscar')) {            
                $usuario = Usuarios::where('id', $id)->first();

                $relacionUsuarioAulas = $usuario->aulas;
                
                return View('buscarUsuario', ['users' => $usuario], ['aulas' => $relacionUsuarioAulas]);
            
            
            } else if ($request->input('editar')) {
                //$usuario = Usuario::where('id', $id)->get();
                $usuario = Usuarios::where('id', $id)->first();
                return View('editarUsuario', ['users' => $usuario]);
                //return View('editarUsuario');

            } else if ($request->input('borrar')) {
                
                $usuario = Usuarios::where('id', $id)->delete();
                return redirect('mostrarUsuarios')
                            ->with('success','Usuario borrado correctamente');
            }
        } else {
            //echo "get";

            $oldID = $request->old('id');

            if (!$oldID == null) {
                $usuario = Usuarios::all()->first();
                $usuario->dni = "";
                $usuario->nombre = "";
                $usuario->apellido = "";
                $usuario->edad = "";
                $usuario->email = "";
                $usuario->id = -1;

                return View('editarUsuario', ['users' => $usuario]);
            } else {
                return redirect('mostrarUsuarios');
            }
        }
    }
}
