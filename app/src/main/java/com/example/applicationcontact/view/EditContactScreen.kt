package com.example.applicationcontact.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcontact.ContactViewModel
import com.example.applicationcontact.model.Contact

@Composable
fun EditContactScreen(contactId: Long?, contactViewModel: ContactViewModel, navController: NavController) {
    val contact by contactViewModel.currentContact.collectAsState()
    LaunchedEffect(contactId) {
        contactId?.let { contactViewModel.getContactById(it) }
    }

    var nom by remember { mutableStateOf(TextFieldValue(contact?.nom ?: "")) }
    var prenom by remember { mutableStateOf(TextFieldValue(contact?.prenom ?: "")) }
    var email by remember { mutableStateOf(TextFieldValue(contact?.email ?: "")) }
    var entreprise by remember { mutableStateOf(TextFieldValue(contact?.entreprise ?: "")) }
    var telephone by remember { mutableStateOf(TextFieldValue(contact?.telephone ?: "")) }
    var mobile by remember { mutableStateOf(TextFieldValue(contact?.mobile ?: "")) }
    var adresse by remember { mutableStateOf(TextFieldValue(contact?.adresse ?: "")) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Modifier Contact", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = nom,
            onValueChange = { nom = it },
            label = { Text("Nom") }
        )
        OutlinedTextField(
            value = prenom,
            onValueChange = { prenom = it },
            label = { Text("Prénom") }
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = entreprise,
            onValueChange = { entreprise = it },
            label = { Text("Entreprise") }
        )
        OutlinedTextField(
            value = telephone,
            onValueChange = { telephone = it },
            label = { Text("Téléphone") }
        )
        OutlinedTextField(
            value = mobile,
            onValueChange = { mobile = it },
            label = { Text("Mobile") }
        )
        OutlinedTextField(
            value = adresse,
            onValueChange = { adresse = it },
            label = { Text("Adresse") }
        )
        Spacer(Modifier.height(16.dp))
        Row {
            Button(
                onClick = {
                    val updatedContact = contact?.copy(
                        nom = nom.text,
                        prenom = prenom.text,
                        email = email.text,
                        entreprise = entreprise.text,
                        telephone = telephone.text,
                        mobile = mobile.text,
                        adresse = adresse.text
                    ) ?: Contact(
                        nom = nom.text, prenom = prenom.text, email = email.text,
                        entreprise = entreprise.text, telephone = telephone.text,
                        mobile = mobile.text, adresse = adresse.text
                    )
                    if (contact?.id == 0L) {
                        contactViewModel.addContact(updatedContact)
                    } else {
                        contactViewModel.updateContact(updatedContact)
                    }
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text("Sauvegarder", color = Color.White)
            }
        }
    }
}
