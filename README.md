FlatDrawableBuilder
===================

Small and simple library for creating flat `android.graphics.drawable.Drawable` instances programmatically.

![Example Image][1]


Usage
=====
```java
Drawable drawable = DrawableBuilder.create(StateBuilder.create()
			.setColor("#33B5E5")
			.setCornerRadius(5)
			.setVolume(Volume.BOTTOM, 4, "#0099CC")
			.setPadding(10)
			.setStroke(2, "#0099CC"))
	.addState(State.PRESSED, StateBuilder.create()
			.setColor("#33B5E5")
			.setCornerRadius(5)
			.setVolume(Volume.NONE, 4, "#0099CC")
			.setPadding(10)
			.setStroke(2, "#0099CC")
	.build();
	
BackgroundSetter.setBackgroundDrawable(drawable, view);
````

[1]: https://raw.githubusercontent.com/Gary111/FlatDrawableBuilder/master/sample/etc/screens/demo_1.png
