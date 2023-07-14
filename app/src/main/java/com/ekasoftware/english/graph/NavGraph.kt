package com.ekasoftware.english.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.view.HomeScreen
import com.ekasoftware.english.view.books.BookDetails
import com.ekasoftware.english.view.translator.Translate
import com.ekasoftware.english.view.books.Books
import com.ekasoftware.english.view.chatbot.ui.ChatScreen
import com.ekasoftware.english.view.mynotes.ui.AddNote.AddNote
import com.ekasoftware.english.view.mynotes.ui.UpdateNote.UpdateNote
import com.ekasoftware.english.view.settings.Settings
import com.ekasoftware.english.view.stories.StoryDetails
import com.ekasoftware.english.view.translator.viewmodel.TranslatorViewModel
import com.ekasoftware.english.view.vocablarylist.model.Word
import com.ekasoftware.english.view.vocablarylist.view.AllWordScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    translatorViewModel: TranslatorViewModel,
    wordList : List<Word>
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Settings.route) {
            Settings(navController = navController)
        }

        composable(route = Screen.Books.route) {
            Books(navController = navController)
        }

        composable(route = Screen.AddNote.route) {
            AddNote(navController = navController)
        }

        composable(
            route = "updatenote/{id}/{title}/{comment}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
                navArgument("title") {
                    type = NavType.StringType
                },
                navArgument("comment") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val comment = backStackEntry.arguments?.getString("comment") ?: ""

            UpdateNote(navController = navController, id = id, title = title,comment = comment)
        }

        composable(
            route = "${Screen.StoryDetails.route}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){ backStackEntry ->
            val index = backStackEntry.arguments?.getInt("id") ?: 0
            StoryDetails(navController = navController, index = index)
        }

        composable(
            route = "${Screen.BookDetails.route}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("id") ?: 0
            BookDetails(navController = navController, index = index)
        }

        composable(route = Screen.Translate.route) {
            Translate(navController = navController, viewModel = translatorViewModel)
        }

        composable(route = Screen.ChatBot.route) {
            ChatScreen(navController)
        }
        composable(route = Screen.AllWordScreen.route) {
            AllWordScreen(wordList = wordList)
        }
    }
}