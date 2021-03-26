import { NativeModules } from "react-native";
import { darkMode } from "../../../app/custom-variables.js";
import { background, font, navigation } from "../../../core/variables";
/*
==========================================================================
    TopBar / BottomBar / ProgressOverlay

    Default Class For Mendix TopBar, BottomBar and ProgressOverlay
==========================================================================
*/
// backgroundColor of the native iOS statusbar can not be changed.
// To fix this we change the barStyle of the statusbar if OS theme is dark and app theme is light (And the other way around).
const isOSDarkMode = NativeModules && NativeModules.RNDarkMode && NativeModules.RNDarkMode.initialMode && NativeModules.RNDarkMode.initialMode === "dark";
const statusBarStyle = !darkMode && isOSDarkMode ?
    "dark-content" :
    darkMode && !isOSDarkMode ? "light-content" : navigation.statusBar.barStyle;
//
export const Layout = {
    sidebar: {
    // All ViewStyle properties are allowed
    },
    statusBar: {
        // Only backgroundColor and barStyle are allowed
        backgroundColor: navigation.statusBar.backgroundColor,
        barStyle: statusBarStyle,
    },
    header: {
        container: {
            // All ViewStyle properties are allowed
            backgroundColor: navigation.topBar.backgroundColor,
            borderBottomWidth: undefined,
        },
        title: {
            // All TextStyle properties are allowed
            color: navigation.topBar.titleColor,
            fontSize: navigation.topBar.titleFontSize,
            fontFamily: font.family,
            fontWeight: font.weightBold,
        },
        backButtonText: {
            // All TextStyle properties are allowed
            color: navigation.topBar.backButtonColor,
            fontFamily: font.family,
        },
        backButtonIcon: {
            // All ImageStyle properties are allowed
            tintColor: navigation.topBar.backButtonColor,
        },
    },
    container: {
        // All ViewStyle properties are allowed
        backgroundColor: background.primary,
    },
};
//
export const Page = Layout;
