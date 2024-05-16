import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.applicationcontact.ContactViewModel
import com.example.applicationcontact.model.Contact
import com.example.applicationcontact.view.ContactDetailsScreen
import com.example.applicationcontact.view.EditContactScreen
import io.objectbox.BoxStore

@Composable
fun NavigationGraph(navController: NavHostController, boxStore: BoxStore) {
    val contactBox = boxStore.boxFor(Contact::class.java)
    val contactViewModel = remember { ContactViewModel(contactBox) }

    NavHost(navController, startDestination = "contactList") {
        composable("contactList") {
            ContactListScreen(contactViewModel, navController)
        }
        composable("contactDetails/{contactId}") { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")?.toLong() ?: 0
            ContactDetailsScreen(contactId, contactViewModel, navController)
        }
        composable("editContact/new") {
            // Réinitialiser l'état du contact courant avant de naviguer
            contactViewModel.clearCurrentContact()
            EditContactScreen(null, contactViewModel, navController)
        }
        composable("editContact/{contactId}") { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")?.toLong()
            EditContactScreen(contactId, contactViewModel, navController)
        }
    }
}
