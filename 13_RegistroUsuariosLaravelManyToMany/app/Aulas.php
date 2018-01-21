<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Aulas extends Model
{
    //

    // asociar modelo con tabla
    protected $table = 'aulas';

    // campos a modificar
    protected $fillable = ['num_aula',];


    // Relacion con tabla Usuarios
    public function usuarios() {
        return $this->belongsToMany('App\Usuarios')
          ->withTimestamps();
    }
}
