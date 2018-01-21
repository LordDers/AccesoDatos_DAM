<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Usuarios extends Model
{
    // asociar modelo con tabla
    protected $table = 'usuarios';

    // campos a modificar
    protected $fillable = ['dni', 'nombre', 'apellido', 'edad', 'email',];


    // Relacion con tabla aulas
    public function aulas() {
        return $this->belongsToMany('App\Aulas')
          ->withTimestamps();
    }
}
