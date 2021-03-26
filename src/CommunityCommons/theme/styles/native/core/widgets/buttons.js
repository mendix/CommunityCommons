import { button, contrast, font } from "../variables";
/*

DISCLAIMER:
Do not change this file because it is core styling.
Customizing core files will make updating Atlas much more difficult in the future.
To customize any core styling, copy the part you want to customize to styles/native/app/ so the core styling is overwritten.

==========================================================================
    Button

    Default Class For Mendix Button Widget
========================================================================== */
export const ActionButton = {
    container: {
        // Ripplecolor and all ViewStyle properties are allowed
        borderWidth: 1,
        borderStyle: "solid",
        rippleColor: contrast.lowest,
        borderColor: button.primary.borderColor,
        backgroundColor: button.primary.backgroundColor,
        alignItems: "center",
        justifyContent: "center",
        borderRadius: button.borderRadius,
        paddingVertical: button.paddingVertical,
        paddingHorizontal: button.paddingHorizontal,
    },
    icon: {
        // Size and color are allowed
        color: button.primary.color,
        size: button.fontSizeIcon,
    },
    caption: {
        // All TextStyle properties are allowed
        color: button.primary.color,
        fontSize: button.fontSize,
        fontFamily: font.family,
        fontWeight: button.fontWeight,
    },
};
//
// Default style for button inside a header
export const ActionButtonHeader = {
    container: {
        borderColor: button.header.borderColor,
        backgroundColor: button.header.backgroundColor,
        paddingLeft: button.header.paddingLeft,
        paddingRight: button.header.paddingRight,
    },
    icon: {
        color: button.header.color,
        size: button.header.fontSizeIcon,
    },
    caption: {
        color: button.header.color,
        fontSize: button.header.fontSize,
        fontFamily: font.family,
    },
};
