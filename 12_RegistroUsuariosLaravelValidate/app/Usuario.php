<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Usuario extends Model
{
    // asociar modelo con tabla
    protected $table = 'usuarios';

    // campos a modificar
    protected $fillable = ['dni', 'nombre', 'apellido', 'edad', 'email',];
}
