
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.applicationcontact.ContactViewModel
import com.example.applicationcontact.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(contactViewModel: ContactViewModel, navController: NavController) {
    val contacts by contactViewModel.contacts.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Contacts") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("editContact/new") }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Contact")
            }
        }
    ) { innerPadding ->
        ContactList(contacts, navController, innerPadding)
    }
}

@Composable
fun ContactList(contacts: List<Contact>, navController: NavController, padding: PaddingValues) {
    LazyColumn(contentPadding = padding) {
        items(contacts) { contact ->
            ContactItem(contact, navController)
        }
    }
}

@Composable
fun ContactItem(contact: Contact, navController: NavController) {
    Row(
        modifier = Modifier
            .clickable { navController.navigate("contactDetails/${contact.id}") }
            .padding(all = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = "Profile Picture",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("${contact.nom} ${contact.prenom}", style = MaterialTheme.typography.bodyLarge)
            Text(contact.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
