import { inter } from "@/ui/fonts";
import Image from "next/image";

const people: Person[] = [
  {
    name: "Neil Sims",
    email: "neilsims@devcode.com",
    imageSrc: "",
    amount: 320,
    initials: "NS",
  },
  {
    name: "Bonnie Green",
    email: "bonniegreen@devcode.com",
    imageSrc: "",
    amount: 3467,
    initials: "BG",
  },
  {
    name: "Michael Gough",
    email: "michaelgough@devcode.com",
    imageSrc: "",
    amount: 67,
    initials: "MG",
  },
  {
    name: "Lana Byrd",
    email: "lanabyrd@devcode.com",
    imageSrc: "",
    amount: 367,
    initials: "LB",
  },
  {
    name: "Thomes Lean",
    email: "thomeslean@devcode.com",
    imageSrc: "",
    amount: 2367,
    initials: "TL",
  },
];

interface Person {
  name: string;
  email: string;
  imageSrc: string;
  amount: number;
  initials: string;
}

export default function ListExampleLayout() {
  return (
    <>
      <div className="container my-5 ">
        <div className="container">
          <p
            className={`my-4 uppercase text-3xl underline text-center font-bold tracking-wide`}
          >
            consuming apirest in springboot to view list
          </p>
        </div>
        <div className="flex items-center justify-center h-full">
          <div className="w-full max-w-md p-4 bg-white border border-gray-200 rounded-lg shadow sm:p-8 dark:bg-slate-900 dark:border-gray-500">
            <div className="flex items-center justify-between mb-4">
              <h5 className="text-xl font-bold leading-none text-gray-900 dark:text-white">
                Latest Customers
              </h5>
              <a
                href="#"
                className="text-sm font-medium text-blue-600 hover:underline dark:text-blue-500"
              >
                View all
              </a>
            </div>

            <div className="">
              <ul
                role="list"
                className="divide-y divide-gray-200 dark:divide-gray-700"
              >
                {people.map((person: Person, index: number) => (
                  <li key={index} className="py-3 sm:py-4">
                    <div className="flex items-center">
                      <div className="flex-shrink-0">
                        <div className="relative inline-flex items-center justify-center rounded-full overflow-hidden w-10 h-10 dark:bg-gray-100 bg-gray-500">
                          {person.imageSrc && person.imageSrc !== "" ? (
                            <Image
                              src={person.imageSrc}
                              alt={`${person.name || "NN"} image profile`}
                              width={32}
                              height={32}
                            />
                          ) : (
                            <span className="font-medium text-gray-100  dark:text-gray-600 ">
                              {person.initials || "NN"}
                            </span>
                          )}
                        </div>
                      </div>
                      <div className="flex-1 min-w-0 ms-4">
                        <p className="text-sm font-medium text-gray-900 truncate dark:text-white">
                          {person.name}
                        </p>
                        <p className="text-sm text-gray-500 truncate dark:text-gray-400">
                          {person.email}
                        </p>
                      </div>
                      <div className="inline-flex items-center text-base font-semibold text-gray-900 dark:text-white">
                        ${person.amount}
                      </div>
                    </div>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
