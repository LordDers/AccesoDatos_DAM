<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAulasUsuariosTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('aulas_usuarios', function (Blueprint $table) {
            $table->integer('aulas_id')->unsigned()->nullable();
            $table->foreign('aulas_id')->references('id')
                ->on('aulas')->onDelete('cascade');

            $table->integer('usuarios_id')->unsigned()->nullable();
            $table->foreign('usuarios_id')->references('id')
                ->on('usuarios')->onDelete('cascade');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('aulas_usuarios');
    }
}
