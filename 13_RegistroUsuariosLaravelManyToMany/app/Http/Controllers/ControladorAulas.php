<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Aulas;

class ControladorAulas extends Controller
{

	// Cargar vista de crear aula
    public function cargarFormularioAulas() {
    	return View('anyadirAula');
    }

    // Procesar formulario de crear aula
    public function procesarAnyadirAula(Request $request) {
    	
        // Validate
        $aulas = $request->validate([
            'aula' => 'required|numeric'
        ], [
            'aula.numeric' => 'La edad debe ser numérico'
        ]);


        $aula = new Aulas;

    	// modificar campos del modelo
    	$aula-> num_aula = $request-> input('aula');

    	// registro en la tabla
    	$aula-> save();

        return redirect('mostrarAulas')
        			->with('success','Aula creada correctamente');;
    }

    // Mostrar Aulas
    public function showAulas() {
    	// mostrar todos los usuarios
    	$lista = Aulas::all();

    	// pasar a la vista el array
    	return View('mostrarAulas', ['aulas' => $lista]);
    }

    // Acciones de Aulas
    public function accionesAulas(Request $request) {
        
        $id = $request-> input('id');

        if ($request->isMethod('post')) {
            //echo "post";
            if ($request->input('buscar')) {
                $aula = Aulas::where('id', $id)->first();

                $relacionAulasUsuarios = $aula->usuarios;
                
                return View('buscarAula', ['aula' => $aula], ['usuarios' => $relacionAulasUsuarios]);
                
            } else if ($request->input('editar')) {

            } else if ($request->input('borrar')) {
            }
        } else {
            return redirect('mostrarAulas');
        }
    }

}
