"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import clsx from "clsx";

export default function Header() {
  const links = [
    { href: "/about-us", label: "About us" },
    { href: "/examples", label: "Examples" },
    { href: "/contact", label: "Contact" },
  ];

  const pathname = usePathname();

  return (
    <header>
      <nav className="bg-gray-100 dark:bg-gray-800 dark:border-b dark:border-gray-500">
        <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
          <div className="relative flex h-20 items-center justify-between">
            <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
              {/* <!-- Mobile menu button--> */}
              <button
                type="button"
                className="relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-inset focus:ring-white"
                aria-controls="mobile-menu"
                aria-expanded="false"
              >
                <span className="absolute -inset-0.5"></span>
                <span className="sr-only">Abrir menú principal</span>
                {/* <!-- Icon when menu is closed. Menu open: "hidden", Menu closed: "block" --> */}
                <svg
                  className="block h-6 w-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  aria-hidden="true"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"
                  />
                </svg>
                {/* <!--Icon when menu is open. Menu open: "block", Menu closed: "hidden" --> */}
                <svg
                  className="hidden h-6 w-6"
                  fill="none"
                  viewBox="0 0 24 24"
                  strokeWidth="1.5"
                  stroke="currentColor"
                  aria-hidden="true"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    d="M6 18L18 6M6 6l12 12"
                  />
                </svg>
              </button>
            </div>

            {/* LINKS FOR LG SCREEN */}
            <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
              
              {/* Company Logo */}
              <Link href="/">
                <img
                  className="w-20 dark:invert"
                  src="/logo.svg"
                  alt="DEV CODE"
                />
              </Link>
              
              {/* Links */}
              <div className="hidden sm:ml-6 sm:block">
                <div className="flex mt-5">
                  {links.map((link, index) => (
                    <Link
                      key={index}
                      href={link.href}
                      className={clsx(
                        "bg-gray-500 border text-white rounded-md mx-3 px-3 py-2 text-sm font-medium",
                        {
                          "bg-slate-700 dark:bg-black dark:border dark:border-gray-200":
                            pathname === link.href,
                        }
                      )}
                    >
                      {link.label}
                    </Link>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* <!-- Mobile menu, show/hide based on menu state. --> */}
        <div className="sm:hidden" id="mobile-menu">
          <div className="space-y-1 px-2 pb-3 pt-2">
            {links.map((link, index) => (
              <Link
                key={index}
                href={link.href}
                className={clsx(
                  "bg-gray-400 hover:bg-gray-700 text-white block rounded-md px-3 py-2 text-sm font-medium",
                  {
                    "bg-slate-900 dark:bg-slate-900": pathname === link.href,
                  }
                )}
              >
                {link.label}
              </Link>
            ))}
          </div>
        </div>
      </nav>
    </header>
  );
}
