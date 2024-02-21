"use client";

import TableExampleLayout from "./layouts/table-example-layout";
import OptionsExampleLayout from "./layouts/options-example-layout";
import ListExampleLayout from "./layouts/list-example-layout";

export default function ExamplePage() {
  return (
    <div className="container mx-auto px-3 py-10">
      {/* Layout import */}
      <TableExampleLayout></TableExampleLayout>

      <hr className="my-10 border-t border-gray-300 dark:border-gray-700" />

      {/* Layout import */}
      <ListExampleLayout></ListExampleLayout>

      <hr className="my-10 border-t border-gray-300 dark:border-gray-700" />

      {/* Layout import */}
      <OptionsExampleLayout></OptionsExampleLayout>
    </div>
  );
}
