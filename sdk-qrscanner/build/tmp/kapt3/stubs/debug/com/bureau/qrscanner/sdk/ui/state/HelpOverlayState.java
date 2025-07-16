package com.bureau.qrscanner.sdk.ui.state;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0002\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011R+\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00048F@FX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00048F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\nR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/bureau/qrscanner/sdk/ui/state/HelpOverlayState;", "", "showOverlay", "Landroidx/compose/runtime/MutableState;", "", "(Landroidx/compose/runtime/MutableState;)V", "<set-?>", "isHelpIconVisible", "()Z", "setHelpIconVisible", "(Z)V", "isHelpIconVisible$delegate", "Landroidx/compose/runtime/MutableState;", "value", "isOverlayVisible", "setOverlayVisible", "dismissOverlay", "", "toggleOverlay", "sdk-qrscanner_debug"})
public final class HelpOverlayState {
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState<java.lang.Boolean> showOverlay = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isHelpIconVisible$delegate = null;
    
    public HelpOverlayState(@org.jetbrains.annotations.NotNull()
    androidx.compose.runtime.MutableState<java.lang.Boolean> showOverlay) {
        super();
    }
    
    public final boolean isOverlayVisible() {
        return false;
    }
    
    public final void setOverlayVisible(boolean value) {
    }
    
    public final boolean isHelpIconVisible() {
        return false;
    }
    
    public final void setHelpIconVisible(boolean p0) {
    }
    
    public final void dismissOverlay() {
    }
    
    public final void showOverlay() {
    }
    
    public final void toggleOverlay() {
    }
}