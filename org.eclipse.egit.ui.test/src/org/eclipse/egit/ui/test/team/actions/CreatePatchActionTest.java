 * Copyright (C) 2012, IBM Corporation and others.
import java.io.ByteArrayInputStream;
import java.net.URI;
import org.eclipse.egit.core.CoreText;
import org.eclipse.jgit.util.FileUtils;
	private static final String EXPECTED_WORKSPACE_PATCH_CONTENT = //
	"### Eclipse Workspace Patch 1.0\n" //
			+ "#P "	+ PROJ1	+ "\n"
			+ "diff --git folder/test.txt folder/test.txt\n" //
			+ "index e256dbb..d070357 100644\n" //
			+ "--- folder/test.txt\n" //
			+ "+++ folder/test.txt\n" //
			+ "@@ -1 +1 @@\n" //
			+ "-oldContent\n" //
			+ "\\ No newline at end of file\n" //
			+ "+newContent\n" //
			+ "\\ No newline at end of file\n"
			+ "diff --git folder/test2.txt folder/test2.txt\n"
			+ "deleted file mode 100644\n" //
			+ "index 8f4e8d3..0000000\n" //
			+ "--- folder/test2.txt\n" //
			+ "+++ /dev/null\n" //
			+ "@@ -1 +0,0 @@\n" //
			+ "-Some more content\n" //
			+ "\\ No newline at end of file\n" //
			+ "#P " + PROJ2 + "\n" //
			+ "diff --git test.txt test.txt\n" //
			+ "new file mode 100644\n" //
			+ "index 0000000..dbe9dba\n" //
			+ "--- /dev/null\n" //
			+ "+++ test.txt\n" //
			+ "@@ -0,0 +1 @@\n" //
			+ "+Hello, world\n" //
			+ "\\ No newline at end of file";

	@Test
	public void testNoChangesInSelection() throws Exception {
		IFile fileToStage = touch(PROJ1, "folder/test.txt", "new content in "
				+ PROJ1);
		stage(fileToStage);
		touch(PROJ2, "folder/test.txt", "new content in " + PROJ2);

		CreatePatchWizard.openWizard(PROJ1);

		NoChangesPopup popup = new NoChangesPopup(
				bot.shell(UIText.GitCreatePatchAction_cannotCreatePatch));
		popup.cancelPopup();
	}

	@Test
	public void testWorkspaceHeader() throws Exception {
		touchAndSubmit("oldContent", null);
		touch("newContent");
		URI fileLocationUri = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(PROJ1).getFolder(FOLDER).getFile(FILE2)
				.getLocationURI();
		FileUtils.delete(new File(fileLocationUri));
		IProject secondProject = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(PROJ2);
		IFile newFile = secondProject.getFile(FILE1);
		newFile.create(
				new ByteArrayInputStream("Hello, world".getBytes(secondProject
						.getDefaultCharset())), false, null);
		waitInUI();

		CreatePatchWizard createPatchWizard = openCreatePatchWizard();
		LocationPage locationPage = createPatchWizard.getLocationPage();
		locationPage.selectWorkspace("/" + PROJ1 + "/" + PATCH_FILE);
		OptionsPage optionsPage = locationPage.nextToOptionsPage();
		optionsPage.setFormat(CoreText.DiffHeaderFormat_Workspace);
		createPatchWizard.finish();

		bot.waitUntil(Conditions.shellCloses(createPatchWizard.getShell()));

		IFile patch = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(PROJ1).getFile(PATCH_FILE);
		byte[] bytes = IO.readFully(patch.getLocation().toFile());
		String patchContent = new String(bytes, patch.getCharset());

		assertEquals(EXPECTED_WORKSPACE_PATCH_CONTENT, patchContent);
	}

		CreatePatchWizard.openWizard(PROJ1, PROJ2);