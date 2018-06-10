package deco.sleepp.Models

import java.io.Serializable
import java.util.*

/**
 * Created by admin on 07/04/18.
 */
class Paciente : Serializable{

    var nombre : String? = ""
    var apellidoPaterno : String? = ""
    var apellidoMaterno : String? = ""
    var direccion : String? = ""
    var telefono : String? = ""
    var telefonoCasa : String? = ""
    var sexo : String? = ""
    var idDoctor : Int? = 0
    var password : String? = ""
    var correo : String? = ""
    var idPaciente : String? = ""
}