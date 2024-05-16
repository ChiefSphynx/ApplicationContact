package com.example.applicationcontact.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcontact.ContactViewModel

@Composable
fun ContactDetailsScreen(contactId: Long, contactViewModel: ContactViewModel, navController: NavController) {
    LaunchedEffect(contactId) {
        contactViewModel.getContactById(contactId)
    }

    val contact by contactViewModel.currentContact.collectAsState()

    // Affichage des détails du contact
    contact?.let { currentContact ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Nom: ${currentContact.nom} ${currentContact.prenom}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Téléphone: ${currentContact.telephone}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    "Email: ${currentContact.email}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(20.dp)) // Espacement vertical

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Bouton pour modifier le contact
                    Button(
                        onClick = { navController.navigate("editContact/${currentContact.id}") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Modifier")
                    }
                    // Bouton pour supprimer le contact
                    Button(
                        onClick = {
                            contactViewModel.deleteContact(currentContact)
                            navController.popBackStack() // Retour à la liste après suppression
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Supprimer", color = Color.White)
                    }
                }
            }
        }
    } ?: Text("Contact non trouvé", style = MaterialTheme.typography.bodyLarge)
}
