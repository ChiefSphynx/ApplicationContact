package com.example.applicationcontact

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationcontact.model.Contact
import com.example.applicationcontact.model.Contact_
import io.objectbox.Box
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel(private val contactBox: Box<Contact>) : ViewModel() {
    private val _currentContact = MutableStateFlow<Contact?>(null)
    val currentContact: StateFlow<Contact?> = _currentContact

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    // Charger tous les contacts à l'initialisation
    init {
        loadContacts()
    }

    private fun loadContacts() {
        viewModelScope.launch {
            val sortedContacts = contactBox.query()
                .order(Contact_.nom)
                .order(Contact_.prenom)
                .build()
                .find()
            _contacts.value = sortedContacts
        }
    }

    // Charger un contact spécifique par ID et le mettre à jour dans le StateFlow
    fun getContactById(id: Long) {
        viewModelScope.launch {
            _currentContact.value = contactBox.get(id)
        }
    }

    fun clearCurrentContact() {
        _currentContact.value = null
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            try {
                contactBox.put(contact)
                loadContacts()  // Rafraîchir la liste après l'ajout
            } catch (e: Exception) {
                Log.e("AddContact", "Erreur lors de l'ajout du contact: ${e.message}")
            }
        }
    }


    // Mettre à jour un contact existant et rafraîchir la liste
    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            contactBox.put(contact)
            loadContacts()  // Recharger la liste après mise à jour
        }
    }

    // Supprimer un contact et rafraîchir la liste
    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            contactBox.remove(contact.id)
            loadContacts()  // Recharger la liste après suppression
        }
    }
}
