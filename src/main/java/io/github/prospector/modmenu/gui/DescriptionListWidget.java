package io.github.prospector.modmenu.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.class_4280;

public class DescriptionListWidget extends class_4280<DescriptionListWidget.DescriptionItem> {

	private ModItemWidget lastSelected = null;
	private ModListScreen parent;
	private TextRenderer textRenderer;

	public DescriptionListWidget(MinecraftClient client, int width, int height, int top, int bottom, int entryHeight, ModListScreen parent) {
		super(client, width, height, top, bottom, entryHeight);
		this.parent = parent;
		this.textRenderer = client.textRenderer;
	}

	@Override
	public int getItemWidth() {
		return this.width - 10;
	}

	@Override
	protected int getScrollbarPosition() {
		return this.width - 6 + left;
	}

	@Override
	public void render(int int_1, int int_2, float float_1) {
		if (parent.getModList().getSelectedItem() != lastSelected) {
			lastSelected = parent.getModList().getSelectedItem();
			clearItems();
			capYPosition(-Double.MAX_VALUE);
			if (lastSelected != null && lastSelected.info.getDescription() != null && !lastSelected.info.getDescription().isEmpty())
				for (String line : textRenderer.wrapStringToWidthAsList(lastSelected.info.getDescription().replaceAll("\n", "\n\n"), getItemWidth()))
					children().add(new DescriptionItem(line));
		}
		super.render(int_1, int_2, float_1);
	}

	protected class DescriptionItem extends class_4280.Item<DescriptionItem> {
		protected String text;

		public DescriptionItem(String text) {
			this.text = text;
		}

		@Override
		public void render(int index, int y, int x, int itemWidth, int itemHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
			MinecraftClient.getInstance().textRenderer.drawWithShadow(text, x, y, 0xAAAAAA);
		}
	}

}
