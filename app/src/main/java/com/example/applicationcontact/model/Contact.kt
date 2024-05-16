package com.example.applicationcontact.model
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Contact(

@Id var id: Long = 0,
var nom: String = "",
var prenom: String = "",
var entreprise: String = "",
var telephone: String = "",
var mobile: String = "",
var email: String = "",
var adresse: String = "",
var image: ByteArray? = null  // Assurez-vous que ce champ est bien géré si inclus.
)
