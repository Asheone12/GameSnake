package com.muen.gamesnake.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.muen.gamesnake.R
import com.muen.gamesnake.data.cache.GameCache
import com.muen.gamesnake.presentation.component.AppBar
import com.muen.gamesnake.presentation.component.AppButton
import com.muen.gamesnake.presentation.component.DisplayLarge
import com.muen.gamesnake.presentation.theme.border2dp
import com.muen.gamesnake.presentation.theme.padding16dp
import com.muen.gamesnake.presentation.theme.padding64dp
import com.muen.gamesnake.presentation.theme.width248dp
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(navController: NavHostController) {
    val dataStore = GameCache(LocalContext.current)
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current
    AppBar(
        title = stringResource(R.string.title_settings),
        onBackClicked = { navController.popBackStack() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = padding16dp,
                    start = padding16dp,
                    end = padding16dp
                )
                //.border(width = border2dp, color = MaterialTheme.colorScheme.onBackground),
            ,horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DisplayLarge(
                modifier = Modifier.padding(
                    top = padding64dp,
                    bottom = padding16dp,
                    start = padding16dp,
                    end = padding16dp
                ),
                text = stringResource(id = R.string.player_name),
                textAlign = TextAlign.Center
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
            TextField(
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    textColor = MaterialTheme.colorScheme.onBackground,
                    cursorColor = MaterialTheme.colorScheme.onBackground,
                ),
                singleLine = true,
                modifier = Modifier
                    .focusRequester(focusRequester)
                    .fillMaxWidth()
                    .padding(horizontal = padding64dp)
                    .border(width = border2dp, color = MaterialTheme.colorScheme.onBackground)
            )
            AppButton(
                text = stringResource(R.string.save), modifier = Modifier
                    .width(width248dp)
                    .padding(padding16dp)
            ) {
                scope.launch {
                    dataStore.savePlayerName(text.text.trim())
                    Toast.makeText(context, R.string.player_name_updated, Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
            }
        }
    }
}